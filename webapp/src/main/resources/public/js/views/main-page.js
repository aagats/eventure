define([
    'jquery',
    'underscore',
    'backbone',
    'text!templates/main-page.html',
    'views/event-item',
    '../collections/events'
], function($, _, Backbone, mainPageTemplate, EventItemView, Events){
    var MainPageView = Backbone.View.extend({

        initialize: function() {
            this.events = new Events();
        },

        render: function() {

            $.get('/api/userauth', function(data) {
                window.isAuth = data

                if (data) {
                    this.events.fetch({
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
            var eventView;

            this.events.each(function(event) {
                eventView = new EventItemView({
                    el: this.$('.event-list'),
                    model: event
                });
                eventView.render();
            });
        },

        renderButton: function() {
            this.$el.html("<button class='btn btn-large'><a href='login'>Login with Instagram</a></button>");
        }
    });
    return MainPageView;
});