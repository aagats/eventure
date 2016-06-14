define([
    'jquery',
    'underscore',
    'backbone',
    'text!templates/event-item.html',
    'models/event'
], function($, _, Backbone, eventItemTemplate, Event){
    var EventItemView = Backbone.View.extend({
        

        render: function() {
            var hashtag = this.model.get('hashtag');
            if (hashtag !== null && hashtag !== "") {
                $.get('photos/' + this.model.get('hashtag'), this.renderView.bind(this));
            } else {
                this.renderView({});
            }
        },

        renderView: function(instaData){
            
            if (!_.isEmpty(instaData)) {
                instaData = JSON.parse(instaData);
            }
            
            this.$el.html(_.template(eventItemTemplate, {
                model: this.model,
                photos: instaData.data
            }));
        }
    });
    return EventItemView;
});
