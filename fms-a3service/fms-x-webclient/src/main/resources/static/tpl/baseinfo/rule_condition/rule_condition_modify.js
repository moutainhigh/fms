/**
 * Created by qiaomengnan on 2018-05-17.
 */
app.controller('rule_condition_modify_controller', ['$scope', '$http','$modal', '$modalInstance','toaster','ruleCondId', function ($scope, $http,$modal, $modalInstance,toaster,ruleCondId) {

    $scope.ruleCondition={};

    $scope.formValidate = false;

    $scope.submit = false;

    $http.get('rule_condition/findRuleConditionByRuleCondId?ruleCondId='+ ruleCondId).success(function(data){
        $scope.ruleCondition = data.data;
    });

    /**
     * 保存组织机构属性信息
     */
    $scope.modify = function () {

        if(!$scope.form.$invalid) {

            $scope.submit = true;

            $http.put('rule_condition/modifyRuleCondition', $scope.ruleCondition).success(function (data) {
                if (data.code == Response.successCode)
                    $scope.close(Response.successMark);
                else
                    modalAlert($modal,data.message);
                $scope.submit = false;
            }).error(function(data){
                modalAlert($modal,data);
                $scope.submit = false;
            })

        }else{
            $scope.formValidate = true;
            toaster_info(promptInfo.submitWarn,toaster);
        }


    }

    /**
     * 关闭窗口
     * @param status
     */
    $scope.close = function(status){
        $modalInstance.close(status);
    };

}]);


