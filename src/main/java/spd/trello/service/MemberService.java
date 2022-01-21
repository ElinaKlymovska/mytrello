package spd.trello.service;

import spd.trello.domain.Member;
import spd.trello.repository.IRepository;

public class MemberService extends ServiceLayer<Member>{
    public MemberService(IRepository<Member> repository) {
        super(repository);
    }
}
