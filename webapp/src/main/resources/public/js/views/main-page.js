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
            var postView;

            this.posts.each(function(post) {
                postView = new PostView({
                    el: this.$('.post-list'),
                    model: post,
                    item: true
                });
                postView.render();
            });
        },

        renderButton: function() {
            this.$el.html("<button class='btn btn-large'><a href='login'>Login with Instagram</a></button>");
        }
    });
    return MainPageView;
});