define([
    'jquery',
    'underscore',
    'backbone',
    'text!templates/watches-page.html',
    'views/post',
    'collections/watches'
], function($, _, Backbone, watchesPageTemplate, PostView, Watches){
    var WatchesPageView = Backbone.View.extend({

        initialize: function() {
            this.watches = new Watches();
        },

        render: function() {
            this.watches.fetch({
                success: function (collection, response, options) {
                    this.renderView();
                }.bind(this)
            });
        },

        renderView: function(){

            this.$el.html(_.template(watchesPageTemplate));

            this.watches.each(function(post) {
                var postView = new PostView({
                    model: post,
                    item: true
                });
                this.$('.post-list').append(postView.render().el);
            });
        }
    });
    return WatchesPageView;
});