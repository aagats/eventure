define([
    'jquery',
    'underscore',
    'backbone',
    'text!templates/main-page.html',
    'text!templates/new-post.html',
    'views/post',
    'collections/posts',
    'models/place',
    'models/post',
    'models/event'
], function($, _, Backbone, mainPageTemplate, newPostTemplate, PostView, Posts, Place, Post, Event){
    var MainPageView = Backbone.View.extend({

        initialize: function() {
            this.posts = new Posts();
        },

        events: {
            'click .add-event': 'renderEventForm',
            'click .submit-event': 'addEvent'
        },

        render: function() {

            $.get('/api/userauth', function(isAuth) {
                window.isAuth = isAuth;

                if (isAuth) {
                    if(this.posts.models.length === 0) {
                        this.posts.fetch({
                            success: function (collection, response, options) {
                                this.renderView();
                            }.bind(this)
                        });
                    } else {
                        this.renderView();
                    }

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
        },

        renderEventForm: function() {
            this.$el.html(_.template(newPostTemplate));
        },

        addEvent: function (e) {
            e.preventDefault();
            var form = this.$('form');

            if (form[0].checkValidity()) {
                var data = form.serializeArray(),
                    event = {},
                    location = {};

                _.forEach(data, function(field) {
                    var name = field.name.split('\.');
                    if (name.length > 1) {
                        location[name[1]] = field.value;
                    } else {
                        event[field.name] = field.value;
                    }
                });
                event.location = new Place(location);
                event.tickets = (event.tickets !== undefined);
                event = new Event(event);
                this.posts.add(new Post({event: event, id: _.uniqueId()}));
                this.renderView();
            } else {
                alert("Provide at least name and city.");
            }
        }
    });
    return MainPageView;
});