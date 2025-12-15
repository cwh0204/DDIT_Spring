package hello.core.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    MemberService service = new MemberServiceImpl();

    @Test
    void join() {

        //given
        Member member = new Member(Grade.VIP, "memberA", 1L);
        //when
        service.join(member);
        Member findMember = service.findMember(1L);
        //then

        Assertions.assertThat(member).isEqualTo(findMember);

    }
}
