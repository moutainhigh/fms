/**
 * Created by wubaoliang on 2018-03-12.
 */
app.controller('sys_tpl_msg_modify_controller', ['$scope', '$http','$modal', '$location','toaster', function ($scope, $http,$modal, $location,toaster) {

    $scope.sysTpl={};

    $scope.formValidate = false;

    $scope.submit = false;

    $http.get('sys_tpl/findSysTplVoByTplId?tplId='+ $location.search()['tplId']).success(function(data){
        $scope.sysTpl = data.data;
        $scope.tplItemList = $scope.sysTpl.tplItemList
    });

    // 启用标识
    $scope.enableFlagList = CommonCodeUtils.getCommonCodes(CommonCodeUtils.codeTypes.enableFlag);

    /**
     * 选择短信模板类型
     */
    $scope.selectSysTplType = function () {
        var rtn = $modal.open({
            backdrop : 'static',
            size:'lg',
            templateUrl: 'tpl/system/sys_tpl_type_msg/sys_tpl_type_list_select.html'+getCacheTime(),
            controller: 'sys_tpl_type_select_controller',
            resolve:{
                tplType: function () {
                    return '1'; // 模板类型：短信
                }
            }
        });
        rtn.result.then(function (status) {
            if(status != null) {
                $scope.sysTpl.tplTypeName = status.tplTypeName;
                $scope.sysTpl.tplTypeKey = status.tplTypeKey;
                $scope.sysTpl.tplContent = status.tplContent;
                $http.get('sys_tpl_item/findSysTplItemsByTplTypeKey?tplTypeKey=' + status.tplTypeKey).success(function (data) {
                    $scope.tplItemList = data.data;
                });
            }
        },function(){
        });
    };

    /**
     * 保存组织机构属性信息
     */
    $scope.modify = function () {

        if(!$scope.form.$invalid) {

            $scope.submit = true;

            $http.put('sys_tpl/modifySysTpl', $scope.sysTpl).success(function (data) {
                if (data.code == Response.successCode) {
                    // toaster_success('编辑短信模板信息成功', toaster);
                    $location.path("app/system_sys_tpl_msg_list").search({type: 'modify'});
                } else {
                    modalAlert($modal,data.message);
                }
                $scope.submit = false;
            }).error(function(data){
                modalAlert($modal,data);
                $scope.submit = false;
            })

        }else{
            $scope.formValidate = true;
            toaster_info(promptInfo.submitWarn,toaster);
        }
    };

    /**
     * 返回
     * @param status
     */
    $scope.goBack = function(status){
        $location.path("app/system_sys_tpl_msg_list");
    };

}]);


