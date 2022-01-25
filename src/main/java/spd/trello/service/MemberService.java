package spd.trello.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import spd.trello.domain.Member;
import spd.trello.repository.IRepository;
@Service
public class MemberService extends ServiceLayer<Member>{
    public MemberService(IRepository<Member> repository) {
        super(repository);
    }
}
