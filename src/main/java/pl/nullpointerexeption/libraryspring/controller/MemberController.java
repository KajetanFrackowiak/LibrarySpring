package pl.nullpointerexeption.libraryspring.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.nullpointerexeption.libraryspring.logger.MemberLogger;
import pl.nullpointerexeption.libraryspring.model.Member;
import pl.nullpointerexeption.libraryspring.service.MemberService;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

@Slf4j
@RestController
@RequestMapping("/members")
public class MemberController {

    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberLogger memberLogger;

    public void logMessages() {
        List<Member> allMembers = memberService.getAllMembers();
        log.info("Getting all members. Total members: {}", allMembers.size());
        if (!allMembers.isEmpty()) {
            Member firstMember = allMembers.get(0);
            log.debug("First member details: {}", firstMember);
        }
        try {
            Member savedMember = memberService.saveMember(new Member());
            log.info("Saved member: {}", savedMember);
        } catch (Exception e) {
            log.error("An error occurred: {}", e.getMessage(), e);
        }
    }

    @GetMapping
    public List<Member> getAllMembers() {
        return memberService.getAllMembers();
    }

    @GetMapping("/{id}")
    public Member getMemberById(@PathVariable Long id) {
        return memberService.getMemberById(id);
    }

    @PostMapping
    public Member saveMember(@RequestBody Member member) {
        return memberService.saveMember(member);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Member> updateMember(@PathVariable Long id, @RequestBody Member updatedMember) {
        Member member = memberService.getMemberById(id);

        if (member == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        updatedMember.setMemberID(id);
        Member updated = memberService.updateMember(updatedMember);

        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
    }

    @GetMapping("/logs")
    public Stream<String> getLogs() throws IOException {
        return memberLogger.readLogFile("logs/app.log");
    }
}