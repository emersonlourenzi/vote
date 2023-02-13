package com.vote.contract.v1.voting;

import javax.validation.Valid;

import com.vote.contract.v1.voting.mapper.response.VoteImplResponseToContract;
import com.vote.contract.v1.voting.mapper.response.VoteResultImplResponseToContractMapper;
import com.vote.contract.v1.voting.mapper.response.VotingImplResponseToContractMapper;
import com.vote.contract.v1.voting.model.request.VoteContractRequest;
import com.vote.contract.v1.voting.model.request.VotingContractRequest;
import com.vote.contract.v1.voting.model.response.VoteContractResponse;
import com.vote.contract.v1.voting.model.response.VoteResultContractResponse;
import com.vote.contract.v1.voting.model.response.VotingContractResponse;
import com.vote.impl.voting.VoteService;
import com.vote.impl.voting.VotingService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import static com.vote.contract.v1.voting.mapper.request.VoteContractRequestToImplMapper.mapFrom;
import static com.vote.contract.v1.voting.mapper.request.VotingContractRequestToImplMapper.mapFrom;

@RestController
@AllArgsConstructor
@Api(tags = "Serviço de votação")
@RequestMapping(path = "/v1/voting")
public class VotingRestController {
    
    private final VoteService voteService;
    private final VotingService votingService;
    
    @PostMapping("/init")
    public Mono<VotingContractResponse> initVoting(
        @RequestBody
        @Valid VotingContractRequest votingContractRequest) {
        return votingService.initVoting(mapFrom(votingContractRequest))
            .map(VotingImplResponseToContractMapper::mapFrom);
    }
    
    @PostMapping("/ends/{id}")
    public Mono<VotingContractResponse> endsVoting(
        @PathVariable
        String id) {
        return votingService.endsVoting(id)
            .map(VotingImplResponseToContractMapper::mapFrom);
    }
    
    @PostMapping("/vote")
    public Mono<VoteContractResponse> vote(
        @RequestBody
        @Valid VoteContractRequest voteContractRequest) {
        return voteService.vote(mapFrom(voteContractRequest))
            .map(VoteImplResponseToContract::mapFrom);
    }
    
    @GetMapping("/result/{id}")
    public Mono<VoteResultContractResponse> resultVoting(
        @PathVariable
        String id) {
        return voteService.resultVoting(id)
            .map(VoteResultImplResponseToContractMapper::mapFrom);
    }
    
}
