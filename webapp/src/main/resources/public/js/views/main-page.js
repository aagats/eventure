define([
    'jquery',
    'underscore',
    'backbone',
    'text!templates/main-page.html',
    'views/event',
    '../collections/events'
], function($, _, Backbone, mainPageTemplate, EventView, Events){
    var MainPageView = Backbone.View.extend({

        initialize: function() {
            this.events = new Events();
        },

        render: function() {
            this.events.fetch({
                success: function (collection, response, options) {
                    this.renderView();
                }.bind(this)
            });
        },

        renderView: function(){
            this.$el.html(_.template(mainPageTemplate));
            var eventView;

            this.events.each(function(event) {
                eventView = new EventView({
                    el: this.$('.event-list'),
                    model: event
                });
                eventView.render();
            });
        }
    });
    return MainPageView;
});