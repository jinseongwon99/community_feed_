package com.jinsungwon99.fake;

import com.jinsungwon99.post.application.CommentService;
import com.jinsungwon99.post.application.Interfaces.CommentRepository;
import com.jinsungwon99.post.application.Interfaces.LikeCommentRepository;
import com.jinsungwon99.post.application.Interfaces.LikePostRepository;
import com.jinsungwon99.post.application.Interfaces.PostRepository;
import com.jinsungwon99.post.application.PostService;
import com.jinsungwon99.post.repository.FakeCommentRepository;
import com.jinsungwon99.post.repository.FakeLikeCommentRepository;
import com.jinsungwon99.post.repository.FakeLikePostRepository;
import com.jinsungwon99.post.repository.FakePostRepository;
import com.jinsungwon99.user.application.UserRelationService;
import com.jinsungwon99.user.application.UserService;
import com.jinsungwon99.user.application.interfaces.UserRelationRepository;
import com.jinsungwon99.user.application.interfaces.UserRepository;
import com.jinsungwon99.user.repository.FakeUserRelationRepository;
import com.jinsungwon99.user.repository.FakeUserRepository;

//싱글톤으로 Fake 객체 생성
public class FakeObjectFactory {

    private static final UserRepository fakeUserRepository = new FakeUserRepository();
    private static final UserRelationRepository fakeUserRelationRepository = new FakeUserRelationRepository();
    private static final PostRepository fakePostRepository = new FakePostRepository();
    private static final CommentRepository fakeCommentRepository = new FakeCommentRepository();
    private static final LikePostRepository fakeLikePostRepository = new FakeLikePostRepository();
    private static final LikeCommentRepository fakeLikeCommentRepository = new FakeLikeCommentRepository();

    private static final UserService userService = new UserService(fakeUserRepository);
    private static final UserRelationService userRelationService = new UserRelationService(userService,fakeUserRelationRepository);
    private static final PostService postService = new PostService(userService,fakePostRepository,fakeLikePostRepository);
    private static final CommentService commentService = new CommentService(userService,fakeCommentRepository,postService,fakeLikeCommentRepository);

    private FakeObjectFactory(){}
}
