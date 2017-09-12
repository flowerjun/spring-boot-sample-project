<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>file Upload Test</title>
<script src="/js/dropzone.js"></script>
<link rel="stylesheet" href="/css/dropzone.css" />
<style>
.dropzone {
	width: 400px;
	margin: 0 auto;
}
</style>
</head>
<body>
	<form action="/uploadFile" class="dropzone" id="fileUpload">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
		<div class="fallback">
			<input name="file" type="file" multiple />
		</div>
	</form>
	<script>
		Dropzone.options.fileUpload = {
			createImageThumbnails : false,
			maxFiles : 10,
			addRemoveLinks : "yes"
		};
	</script>
</body>
</html>