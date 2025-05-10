<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'default.doctor.label', default: 'Doctor')}" />
    <title><g:message code="default.doctor.list.label"/></title>
    <asset:link rel="icon" href="favicon.ico" type="image/x-ico" />
</head>
<body>
<section id="students">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <nav aria-label="breadcrumb">
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                        <li class="breadcrumb-item active" aria-current="page"><g:message code="default.doctor.list.label"/></li>
                    </ol>
                </nav>
            </div>
            <div class="col-md-12">
                <g:form class="form" action="index" method="GET">
                    <div class="row">
                        <div class="form-group col-md-4">
                            <label for="firstName"><g:message code="default.doctor.firstName.label"/></label>
                            <div class="input-group">
                                <input type="text" class="form-control" name="firstName" value="${params?.firstName}"
                                       id="firstName">
                            </div>
                        </div>
                    </div>
                    <div>
                        <a href="${createLink(controller: 'doctor', action: 'index')}" class="btn btn-warning">
                            <i class="fa fa-times"></i> <g:message code="default.cancel.label" />
                        </a>
                        <button type="submit" class="btn btn-primary">
                            <i class="fa fa-filter"></i> <g:message code="default.filter.label"/>
                        </button>
                    </div>
                </g:form>
                <div class="card mt-2">
                    <div class="card-header">
                        <div class="row">
                            <div class="col-md-6">
                                <h4><g:message code="default.doctor.list.label"/></h4>
                            </div>
                        </div>
                    </div>
                    <div class="card-body">
                        <table class="table table-bordered table-striped">
                            <thead class="bg-custom">
                            <tr>
                                <th><g:message code="default.doctor.firstName.label"/></th>
                                <th><g:message code="default.doctor.lastName.label"/></th>
                                <th><g:message code="default.doctor.specialty.label"/></th>
                            </tr>
                            </thead>
                            <tbody>
                            <g:each var="doctor" in="${doctorList}">
                                <tr>
                                    <td>${doctor?.firstName}</td>
                                    <td>${doctor?.fatherLastName} ${doctor?.motherLastName}</td>
                                    <td>${doctor?.specialty}</td>
                                </tr>
                            </g:each>
                            </tbody>
                        </table>
                        <div class="center">
                            <ul class="pagination">
                                <g:paginate total="${doctorListCount ?: 0}" params="${params}" />
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
</body>
</html>
