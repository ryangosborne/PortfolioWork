<?php $__env->startSection('content'); ?>
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card">
                    <table class="table table-striped">
                        <tr>
                            <th colspan="3">
                                <h4 class="pt-2">User Details</h4>
                            </th>
                            <th colspan="1">
                                <a href="/admin/users" class="btn btn-primary" style="float:right">Back to List</a>
                            </th>
                        </tr>
                        <tbody>
                            <td>
                                Name: <?php echo e(dd($user)); ?>

                            </td>
                            <td>
                                <a class="btn btn-warning" href="" role="button">Edit</a>
                                <a class="btn btn-danger" href="" role="button">Delete</a>
                            </td>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
<?php $__env->stopSection(); ?>

<?php echo $__env->make('layouts.app', \Illuminate\Support\Arr::except(get_defined_vars(), ['__data', '__path']))->render(); ?><?php /**PATH /Users/ryan/Documents/Programming/INET2005 PHP/laravel/finalassignment-ryangosborne/SMFeed-w0201814/resources/views/user/user.blade.php ENDPATH**/ ?>