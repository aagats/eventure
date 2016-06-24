define([
    'jquery',
    'underscore',
    'backbone',
    'text!templates/main-page.html',
    'text!templates/new-post.html',
    'text!templates/place-form.html',
    'views/post',
    'collections/posts',
    'collections/places',
    'models/place',
    'models/post',
    'models/event'
], function($, _, Backbone, mainPageTemplate, newPostTemplate, placeFormTemplate, PostView, Posts, Places, Place, Post, Event){
    var MainPageView = Backbone.View.extend({

        initialize: function() {
            this.posts = new Posts();
        },

        events: {
            'click .add-event': 'renderEventForm',
            'click .submit-event': 'addEvent',
            'click .hide-form': 'renderView',
            'click .submit-place': 'addPlace'
        },

        render: function() {
            $.get('/api/userauth', function(isAuth) {
                if (isAuth !== '') {
                    if (!window.user) {
                        window.user = isAuth.username;
                        window.isAdmin = (isAuth.role == 'ADMIN');
                    }
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
            this.$el.html('<a href="login" class="btn btn-large btn-warning center-block login-btn" role="button">Login with Instagram</a>');
        },

        renderEventForm: function() {
            if (!this.places) {
                this.places = new Places();
            }
            
            this.places.fetch()
                .done(function(data) {
                    this.$el.html(_.template(newPostTemplate, {
                        places: this.places
                    }));
                }.bind(this));
        },

        addEvent: function (e) {
            e.preventDefault();
            var form = this.$('form.new-post');

            if (form[0].checkValidity()) {
                var data = form.serializeArray();
                    event = {};

                _.forEach(data, function(field) {
                    event[field.name] = field.value;
                });

                event.dateTime = event.dateTime.replace('T', ' ');
                event.location = this.$('select').val();
                event.tickets = (event.tickets !== undefined);
                var eventModel = new Event();
                eventModel.save(event, {
                    type: 'post'
                })
                    .done(function(data) {
                        var post = new Post();
                        post.save({event: eventModel.get('id')})
                            .done(function(data) {
                                this.posts.add(post);
                                this.renderView();
                            }.bind(this));
                    }.bind(this));

            } else {
                alert("Provide at least name and city.");
            }
        },

        addPlace: function() {
            var place = new Place({
                name: this.$('input[name="location.name"]').val(),
                city: this.$('input[name="location.city"]').val(),
                street: this.$('input[name="location.street"]').val(),
                buildingNumber: this.$('input[name="location.buildingNumber"]').val()
            });
            place.save()
                .done(function() {
                   this.places.add(place);
                    this.$('select').append('<option value="' + place.get('id') + '">' + place.get('name') + ' - ' + place.get('city')+ '</option>');
                    this.$('#place-modal').modal('hide');
                }.bind(this));
        }
    });
    return MainPageView;
});