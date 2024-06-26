package org.example.dollarreview.review.controller;

import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.dollarreview.review.dto.ReviewRequest;
import org.example.dollarreview.review.dto.ReviewResponse;
import org.example.dollarreview.review.service.ReviewService;
import org.example.share.config.global.security.UserDetailsImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products/{productId}")
public class ReviewController {
    private final ReviewService reviewService;
    @PostMapping("/reviews")
    public ResponseEntity<String> createReview(
        @PathVariable Long productId,
        @RequestBody ReviewRequest reviewRequest,
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        reviewService.createReview(productId, reviewRequest, userDetails.getUser().getId());
        return ResponseEntity.status(HttpStatus.CREATED).body("후기가 등록되었습니다.");
    }
    @GetMapping("/reviews")
    //리뷰 전체 조회
    public ResponseEntity<List<ReviewResponse>> getAllReviews(
        @PathVariable Long productId
    ) {
        List<ReviewResponse> responseList = reviewService.getAllReviews(productId);
        return ResponseEntity.ok().body(responseList);
    }
    //리뷰 삭제
    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<String> deleteReview(
        @PathVariable Long reviewId,
        @PathVariable Long productId,
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ){
        reviewService.deleteReview(reviewId,userDetails.getUser().getId(),productId);
        return ResponseEntity.ok().body("리뷰가 삭제 되었습니다.");
    }
    //리뷰 수정
    @PutMapping("/reviews/{reviewId}")
    public ResponseEntity<String> updateReview(
        @PathVariable Long reviewId,
        @RequestBody ReviewRequest reviewRequest,
        @PathVariable Long productId,
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        reviewService.updateReview(reviewId,reviewRequest,userDetails.getUser().getId(),productId);
        return ResponseEntity.ok().body("리뷰가 수정 되었습니다.");
    }

    @GetMapping("reviews/{reviewId}/image")
    public String getReviewImage(@PathVariable Long reviewId) throws IOException{
        return reviewService.getReviewImage(reviewId);
    }

    @PostMapping("reviews/{reviewId}/image")
    public void uploadReviewImage(@PathVariable Long reviewId, @RequestParam("file") MultipartFile file) throws IOException {
        reviewService.uploadReviewImage(reviewId,file);
    }


}
