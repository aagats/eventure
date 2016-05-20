define([
    'jquery',
    'underscore',
    'backbone',
    'text!templates/event.html',
    'models/event'
], function($, _, Backbone, eventTemplate, Event){
    var EventView = Backbone.View.extend({

        render: function() {
            var instagramUrl = 'https://api.instagram.com/v1/tags/makeup/media/recent?access_token=234089457.0c0dacc.f522a7b926764a1aad39fd87d630fe06';

            $.ajax({
                url: instagramUrl,
                method: 'GET',
                headers: {
                    'Access-Control-Allow-Origin': '*'
                },
                success: function(data) {
                    this.renderView(data);
                }.bind(this)
            })
        },

        renderView: function(instaData){
            this.$el.append(_.template(eventTemplate, {
                model: this.model
            }));
            var toAppend='';

            _.forEach(instaData.data, function(photo) {
               toAppend += '<a href="' + photo.link + '"><img src="' + photo.images.thumbnail.url + '"></a>';
            });

            this.$('.photos').html(toAppend);
        }
    });
    return EventView;
});
