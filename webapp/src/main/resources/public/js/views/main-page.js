define([
    'jquery',
    'underscore',
    'backbone',
    'text!templates/main-page.html',
    'text!templates/event.html',
    '../collections/events'
], function($, _, Backbone, mainPageTemplate, eventTemplate, Events){
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

            var toAppend = "";
            this.events.each(function(event) {
                toAppend += _.template(eventTemplate, {
                    model: event
                });
            });
            this.$('.event-list').append(toAppend);
        }
    });
    return MainPageView;
});