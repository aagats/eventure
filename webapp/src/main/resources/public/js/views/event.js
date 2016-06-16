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

        initialize: function() {
            this.listenTo(this.model.get('observators'), 'add', this.renderWatchers.bind(this));
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
            if (this.isWatching()) {
                this.$('.watch-button').hide();
            }
        },

        renderWatchers: function() {
            var toAppend = '',
                observators = this.model.get('observators');

            observators.each(function(observator) {
               toAppend +=  '<span class="label label-default">' + observator.get('username') + '</span>'
            });

            this.$('.watchers').html(toAppend);
        },

        watchEvent: function() {
            $.post(this.model.watchUrl(), function (username) {
                this.$('.watch-button').hide();
                this.model.get('observators').add(new User({username: username}));
            }.bind(this));
        },

        isWatching: function() {
            var filtered = this.model.get('observators').filter(function(observator) {
                if (observator.get('username') === window.user) return true;
            });
            return filtered.length === 1;
        }
    });
    return EventView;
});

