define([
    'jquery',
    'underscore',
    'backbone',
    'text!templates/event.html',
    'models/event'
], function($, _, Backbone, eventTemplate, Event){
    var EventView = Backbone.View.extend({

        render: function() {
            if (this.model.get('hashtag') !== null) {
                $.get('photos/' + this.model.get('hashtag'), this.renderView.bind(this));
            } else {
                this.renderView({});
            }
        },

        renderView: function(instaData){
            
            if (!_.isEmpty(instaData)) {
                instaData = JSON.parse(instaData);
            }
            
            this.$el.append(_.template(eventTemplate, {
                model: this.model,
                photos: instaData.data
            }));
        }
    });
    return EventView;
});
