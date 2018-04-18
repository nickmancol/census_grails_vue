<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title>Dataiku Census Excercise</title>
  </head>
  <body>
    <div id="census-app">
      <div class="">
        <h1>Hi!</h1>
      </div>
      <div class="">
        <select v-model="selected" v-on:change="updateList">
          <option>select</option>
          <option v-for="option in options" v-bind:value="option.value">
            {{ option.text }}
          </option>
        </select>
      </div>
      <div class="">
        <div v-if="total > limit">
          showing {{limit}} rows of {{total}}, {{total - limit}} left.
        </div>
        <div v-else>
          showing {{total}} rows.
        </div>
      </div>
      <div class="">
        <table>
          <thead>
            <tr>
              <th>Value</th>
              <th>Count</th>
              <th>Average Age</th>
            </tr>
          </thead>
          <tbody v-for="row in results">
            <tr>
              <td>{{ row.value }}</td>
              <td>{{ row.count }}</td>
              <td>{{ row.average }}</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/vue"></script>
    <script src="https://cdn.jsdelivr.net/npm/vue-resource@1.5.0"></script>
    <script type="text/javascript">
      var app = new Vue({
        el: '#census-app',
        data: {
          selected: 'select', total: 0, limit:0, offset:0
          , options: []
          , results: []
          , source : '/census'
        }, created: function () {
          this.updateList();
          this.$http.get(this.source).then(response => {
              for (var i = 0; i < response.body.length; i++) {
                this.options.push({'value':response.body[i], 'text':response.body[i]});
              }
            }, response => {
              console.log(response.body);
            }
          );
        }, methods: {
          updateList: function(){
            var url = this.source+'/'+this.selected;
            this.$http.get(url).then(response => {
                this.results = [];
                this.total = response.body.total;
                this.limit = response.body.limit;
                for (var i = 0; i < response.body.results.length; i++) {
                  var row = response.body.results[i];
                  this.results.push({'value':row.value, 'count':row.num, 'average':row.average});
                }
              }, response => {
                console.log(response.body);
              }
            );
          }
        }
      });
    </script>
  </body>
</html>
