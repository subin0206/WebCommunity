var deleteFileList = new Array();
var fileCount = 0;
var totalCount = 0;
var fileNum = 0;
var inputFileList = new Array();
function deleteFile(elem){
    var id = $('#fileInfo').val();
    $(elem).parent().parent().remove();
    if(!deleteFileList.includes(elem.value)){
        deleteFileList.push(elem.value);
    }
}

var main = {
    init : function () {
        var _this = this;
        $('#btn-save').on('click', function () {
            _this.save();
        });

        $('#btn-update').on('click', function () {
            _this.update();
        });

        $('#btn-delete').on('click', function () {
            _this.delete();
        });

        $('#btn-temp').on('click', function(){
            _this.temp();
        })
    },
    temp : function(){
        alert(deleteFileList);
    },
    save : function () {
        var data = {
            title: $('#title').val(),
            content: $('#content').val(),

        };
//        var data2 = {
//            "deleteFile" : deleteFileList
//        };
//        var jsonDeleteFile = JSON.stringify(deleteFileList);
        var form =$('#form')[0];
		var formData = new FormData(form);
		formData.append('file', $('#file'));
		formData.append('key', new Blob([JSON.stringify(data)] , {type: "application/json"}));
		formData.append('deleteFile', new Blob([JSON.stringify(deleteFileList)] , {type: "application/json"}));
//		formData.append('deleteFile', data2);
        $.ajax({
            type: 'POST',
            url: '/api/v1/posts',
            processData: false,
            contentType:false,
            traditional:true,
            data: formData
        }).done(function() {
            alert('글이 등록되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    update : function () {
        var data = {
            title: $('#title').val(),
            content: $('#content').val()
        };
        var form =$('#form')[0];
		var formData = new FormData(form);
		formData.append('file', $('#file'));
//		formData.append('deleteFileList',deleteFileList);
//        formData.append('deleteFileList', new Blob([JSON.parse(JSON.stringify(data))]), {type: "application/json"});
		formData.append('key', new Blob([JSON.stringify(data)] , {type: "application/json"}));

        var id = $('#id').val();

        $.ajax({
            type: 'PUT',
            url: '/api/v1/posts/'+id,
            processData:false,
            contentType:false,
            data: formData
        }).done(function() {
            alert('글이 수정되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    delete : function () {
        var id = $('#id').val();

        $.ajax({
            type: 'DELETE',
            url: '/api/v1/posts/'+id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8'
        }).done(function() {
            alert('글이 삭제되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }

};

main.init();