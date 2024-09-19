package tobyspring.hellospring;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

public class PaymentService {

    public Payment prepare(Long orderId, String currency, BigDecimal foreignCurrencyAmount) throws IOException {
        // 환율 가져오기
        // https://open.er-api.com/v6/latest/
        URL url = new URL("https://open.er-api.com/v6/latest/" + currency); // url 형태로 저장
        HttpURLConnection connection = (HttpURLConnection) url.openConnection(); // URLConnection말고 HttpURLConnection으로 만들면 더 많은 기능을 쓸 수 잇음.
        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream())); // byte형태로 값이 들어오기 때문에 사용
        String response = br.lines().collect(Collectors.joining()); // 버퍼리더의 라인들을 한줄씩 조인해서 들고옴

        ObjectMapper mapper = new ObjectMapper(); // jackson 라이브러리 임포트 해야됨 implementation 'org.springframework.boot:spring-boot-starter-json'
        ExRateData data = mapper.readValue(response, ExRateData.class); // mapper에서 값을 읽고 record의 클래스에 맞는 생성자 형태로 보냄
        BigDecimal exRate = data.rates().get("KRW"); // data의 rates(Map)에서 "KRW"를 가져옴
        br.close();

        // 금액 계산
        BigDecimal convertedAmount = foreignCurrencyAmount.multiply(exRate);

        // 유효 시간 계산
        LocalDateTime validUntil = LocalDateTime.now().plusMinutes(30);


        return new Payment(orderId, currency, foreignCurrencyAmount, exRate, convertedAmount, validUntil);
    }

    public static void main(String[] args) throws IOException {
        PaymentService paymentService = new PaymentService();
        Payment payment = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.7));
        System.out.println(payment);
    }

}
