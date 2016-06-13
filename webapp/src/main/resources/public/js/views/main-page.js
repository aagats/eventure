define([
    'jquery',
    'underscore',
    'backbone',
    'text!templates/main-page.html',
    'views/post',
    'collections/posts'
], function($, _, Backbone, mainPageTemplate, PostView, Posts){
    var MainPageView = Backbone.View.extend({

        initialize: function() {
            this.posts = new Posts();
        },

        render: function() {

            $.get('/api/userauth', function(isAuth) {
                window.isAuth = isAuth;

                if (isAuth) {
                    this.posts.fetch({
                        success: function (collection, response, options) {
                            this.renderView();
                        }.bind(this)
                    });
                } else {
                    this.renderButton();
                }
            }.bind(this));
        },

        renderView: function(){

            this.$el.html(_.template(mainPageTemplate));

            this.posts.each(function(post) {
                var postView = new PostView({
                    model: post,
                    item: true
                });
                this.$('.post-list').append(postView.render().el);
            });
        },

        renderButton: function() {
            this.$el.html('<a href="login" class="btn btn-large btn-warning" role="button">Login with Instagram</a>');
        }
    });
    return MainPageView;
});