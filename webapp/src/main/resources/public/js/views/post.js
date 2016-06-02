define([
    'jquery',
    'underscore',
    'backbone',
    'text!templates/post.html',
    'models/Post',
    'views/event-item',
    'views/event'
], function($, _, Backbone, postTemplate, Post, EventItemView, EventView){
    var PostView = Backbone.View.extend({

        initialize: function(options) {
            this.item = options.item;
        },

        render: function() {
            this.$el.append(_.template(postTemplate, {
                model: this.model
            }));

            var data = {
                model: this.model.get('event'),
                el: this.$('.event')
            };
            var eventView = (this.item ? new EventItemView(data) : new EventView(data));
            if (!this.item) {
                this.$('.see-more').hide();
            }

            eventView.render();
        }
        
    });
    return PostView;
});

