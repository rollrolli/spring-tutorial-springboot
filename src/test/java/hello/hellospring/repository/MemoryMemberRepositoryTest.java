package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*; // 이것이 static import !

class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    // 각각의 테스트 메소드가 실행되고 난 후 실행되는 메소드
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("sora");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();

//        System.out.println("result = " + (result == member));
//        Assertions.assertEquals(result, member);
        assertThat(member).isEqualTo(result);
        // Assertions.assertThat 에서 Assertions 를 static import 하면 그냥 assertThat으로 쓸 수 있다.
        // 단축키는 option + Enter

    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("sora");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("kyungmin");
        repository.save(member2);

        Member result = repository.findByName("sora").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("sora");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("kyungmin");
        repository.save(member2);

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }

}
