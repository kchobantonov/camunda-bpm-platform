/*
 * Copyright Camunda Services GmbH and/or licensed to Camunda Services GmbH
 * under one or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information regarding copyright
 * ownership. Camunda licenses this file to you under the Apache License,
 * Version 2.0; you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

module.exports = [
  "readFiles",
  function(readFiles) {
    return {
      restrict: "A",
      scope: {
        onChange: "&camFile"
      },
      link: function($scope, $element) {
        $element[0].addEventListener("change", function($event) {
          $scope.$apply(function() {
            readFiles($element[0].files).then(function(files) {
              $scope.onChange({
                $event: $event,
                files: files
              });
            });
          });
        });
      }
    };
  }
];