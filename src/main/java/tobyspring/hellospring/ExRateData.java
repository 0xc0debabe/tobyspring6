package tobyspring.hellospring;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true) // Json형태의 값을 받아오는데 필요없는 값들은 무시하겠다.
public record ExRateData(String result, Map<String, BigDecimal> rates) {
}

// record 읽기만 가능 수정 불가능 생성자 형태로 값을 저장시킴
