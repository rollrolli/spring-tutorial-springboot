package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;



// @Component 로 빈 등록해줘도 되지만, config에 직접 등록하는 것을 권장. 특별하니까!
@Aspect
public class TimeTraceAop {

    // 보통 Around는 패키지 단위로 많이 적용함
    // Java Config 에 등록할 때 타겟으로 config 클래스 제외하지 않으면 순환참조 오류 발생
    // 이유는 config의 timeTraceAop() 함수 실행 시에도 이 aop 적용되기 때문
    @Around("execution(* hello.hellospring..*(..)) && !target(hello.hellospring.SpringConfig)")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());
        try {
            return joinPoint.proceed(); // inline shortcut : option + cmd + N
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("FINISH: " + joinPoint.toString() + " " + timeMs + "ms");
        }
    }
}

/*
- 회원가입, 회원 조회 등 핵심 관심 사항과 시간을 측정하는 공통 관심 사항을 분리한다.
- 시간을 측정하는 로직을 별도의 공통 로직으로 만들었다.
- 핵심 관심 사항을 깔끔하게 유지할 수 있다.
- 변경이 필요하면 이 로직만 변경하면 된다.
- 원하는 적용 대상을 선택할 수 있다.
 */
