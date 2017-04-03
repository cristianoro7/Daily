package desperado.com.daily.data.repository.source.factory;

import javax.inject.Inject;

import desperado.com.daily.data.repository.source.interfaces.CommentDataStore;
import desperado.com.daily.data.repository.source.remote.CommentRemoteDataStore;
import desperado.com.daily.presentation.di.PerActivity;

/**
 * Created by desperado on 17-2-3.
 */
@PerActivity
public class CommentFactory {

    private CommentRemoteDataStore commentDataStore;

    @Inject
    public CommentFactory(CommentRemoteDataStore commentDataStore) {
        this.commentDataStore = commentDataStore;
    }

    public CommentDataStore getCommentDataStore() {
        return commentDataStore;
    }
}
