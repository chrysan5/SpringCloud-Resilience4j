package com.spring_cloud.resilience4j.sample;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;


    @GetMapping("/product/{id}")
    public Product getProduct(@PathVariable("id") String id) {
        return productService.getProductDetails(id);
    }

    /*locaohost:19090/product/1,2,..등 아무런 숫자나 할 경우 잘 동작하는 것 확인
    111을 입력할 경우 서킷브레이커가 작동하여 서킷브레이커 메소드가 작동하고,
    이후 111이 아닌 다른 숫자를 입력해도 계속 서킷브레이커 메소드 페이지가 나오다가
    일정 시간이 지나면 다시 다른 숫자를 입력하면 정상적으로 작동하는 것을 확인 가능*/
}

/* localhost:19090/actuator/prometheus 에 가면 현재 애플리케이션의 상태 정보를 볼 수 있다.
밑으로 내려가면 리질리언스4j 서킷브레이커 정보도 있다. 프로메테우스는 이 정보를 가지고 메트릭을 만들며
이 정보를 시각화를 통해서 정보를 한 눈에 볼 수 있고 이 정보를 통해서 알람 등도 작성할 수 있다.
=> 프로메테우스의 정보를 전달해 줄때 리질리언스4j의 서킷브레이커 정보도 같이 전달해 준다.*/