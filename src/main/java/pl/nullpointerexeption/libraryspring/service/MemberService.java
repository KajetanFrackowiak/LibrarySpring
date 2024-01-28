package pl.nullpointerexeption.libraryspring.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.nullpointerexeption.libraryspring.model.Member;
import pl.nullpointerexeption.libraryspring.repository.MemberRepository;

import java.util.List;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public Member getMemberById(Long id) {
        return memberRepository.findById(id).orElse(null);
    }

    public Member updateMember(Member updatedMember) {
        Member existingMember = memberRepository.findById(updatedMember.getMemberID())
                .orElseThrow(() -> new EntityNotFoundException("Member not found"));

        // Aktualizuj pola członka
        existingMember.setMemberName(updatedMember.getMemberName());
        existingMember.setBirthYear(updatedMember.getBirthYear());
        existingMember.setAddress(updatedMember.getAddress());
        existingMember.setEmail(updatedMember.getEmail());

        return memberRepository.save(existingMember);
    }
    public Member saveMember(Member member) {
        return memberRepository.save(member);
    }

    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }
}
