package spd.trello.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import spd.trello.domain.Member;
import spd.trello.repository.IRepository;
import spd.trello.repository.MemberDAO;

@Service
public class MemberService extends ServiceLayer<Member, MemberDAO>{
    @Autowired
    public MemberService(MemberDAO repository) {
        super(repository);
    }
}
