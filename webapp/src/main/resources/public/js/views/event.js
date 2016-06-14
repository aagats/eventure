define([
    'jquery',
    'underscore',
    'backbone',
    'text!templates/event.html',
    'models/event',
    'models/user'
], function($, _, Backbone, eventTemplate, Event, User){
    var EventView = Backbone.View.extend({

        events: {
            'click .watch-button' : 'watchEvent'
        },
        
        render: function() {
            var hashtag = this.model.get('hashtag');
            if (hashtag !== null && hashtag !== "") {
                $.get('/photos/' + this.model.get('hashtag'), this.renderView.bind(this));
            } else {
                this.renderView({});
            }
        },

        renderView: function(instaData){

            if (!_.isEmpty(instaData)) {
                instaData = JSON.parse(instaData);
            }

            this.$el.html(_.template(eventTemplate, {
                model: this.model,
                photos: instaData.data
            }));
        },

        renderWatchers: function() {
            this.$('.watchers').append('<span class="label label-default">Ala</span>');
            this.$('.watch-button').hide();
        },

        watchEvent: function() {
            this.model.get('observators').add(new User({username: 'Ala'}));
            this.model.save()
                .done(this.renderWatchers.bind(this));
        }
    });
    return EventView;
});

