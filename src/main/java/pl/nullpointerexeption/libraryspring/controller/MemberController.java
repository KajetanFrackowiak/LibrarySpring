package pl.nullpointerexeption.libraryspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.nullpointerexeption.libraryspring.model.Member;
import pl.nullpointerexeption.libraryspring.service.MemberService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {

    private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

    @Autowired
    private MemberService memberService;

    @GetMapping
    public List<Member> getAllMembers() {
        logger.info("Getting all members");
        return memberService.getAllMembers();
    }

    @GetMapping("/{id}")
    public Member getMemberById(@PathVariable Long id) {
        logger.info("Getting member with id: {}", id);
        return memberService.getMemberById(id);
    }

    @PostMapping
    public Member saveMember(@RequestBody Member member) {
        logger.info("Saving member: {}", member);
        return memberService.saveMember(member);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Member> updateMember(@PathVariable Long id, @RequestBody Member updatedMember) {
        logger.info("Updating member with id: {}", id);
        Member member = memberService.getMemberById(id);

        if (member == null) {
            logger.warn("Member with id: {} not found", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        updatedMember.setMemberID(id);
        Member updated = memberService.updateMember(updatedMember);

        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteMember(@PathVariable Long id) {
        logger.info("Deleting member with id: {}", id);
        memberService.deleteMember(id);
    }
}