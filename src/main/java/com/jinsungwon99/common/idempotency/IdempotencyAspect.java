package com.jinsungwon99.common.idempotency;

import com.jinsungwon99.common.ui.Response;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component  //스프링에 등록
@RequiredArgsConstructor
public class IdempotencyAspect {

    private final IdempotencyRepository idempotencyRepository;
    private final HttpServletRequest request;

    @Around("@annotation(Idempotent)")  //특정로직으 설정 전과 실행 후를 관여
    public Object checkIdempotency(ProceedingJoinPoint joinPoint) throws Throwable {
        String idempotencyKey = request.getHeader("idempotency-Key");
        if (idempotencyKey == null) {
            return joinPoint.proceed();
        }

        Idempotency idempotency = idempotencyRepository.getByKey(idempotencyKey);

        if(idempotency != null){
            return idempotency.getResponse();   //로직을 수행하지 않고 사용한 응답 값 변환
        }
        Object result = joinPoint.proceed();    //로직을 수행

        Idempotency newIdempotency = new Idempotency(idempotencyKey,(Response<?>) result);
        idempotencyRepository.save(newIdempotency);

        return result;
    }
}
