<?php $__env->startSection('content'); ?>
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card">
                    <table class="table table-striped">

                        <?php if(Session::has('messageSuccess')): ?>
                            <div class="alert-success" style="padding: 20px; font-size: medium">
                                <?php echo e(Session::get('messageSuccess')); ?>

                            </div>
                        <?php endif; ?>
                        <?php if(Session::has('messageDanger')): ?>
                            <div class="alert-danger" style="padding: 20px; font-size: medium">
                                <?php echo e(Session::get('messageDanger')); ?>

                            </div>
                        <?php endif; ?>
                            
                        <tr>
                            <th>
                                <h4 class="pt-2">User Details</h4>
                            </th>
                            <th>
                                <a href="/admin/users" class="btn btn-primary" style="float:right">Back to List</a>
                            </th>
                        </tr>
                        <tbody>
                            <tr class="table-light">
                                <td colspan="8">Name: <?php echo e($user->name); ?><br>
                                Email: <?php echo e($user->email); ?><br>
                                Current Roles:<br>
                                    <ul>
                                        <?php $__currentLoopData = $user->roles; $__env->addLoop($__currentLoopData); foreach($__currentLoopData as $role): $__env->incrementLoopIndices(); $loop = $__env->getLastLoop(); ?><li><?php echo e($role->name); ?></li><?php endforeach; $__env->popLoop(); $loop = $__env->getLastLoop(); ?>
                                    </ul>
                                </td>
                            </tr>
                            <!--
                            <tr>
                                <td colspan="8">
                                        <a class="btn btn-warning" href="" role="button">Edit</a>
                                        <a class="btn btn-danger" href="" role="button">Delete</a>
                                </td>
                            </tr>
                            -->
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
<?php $__env->stopSection(); ?>

<?php echo $__env->make('layouts.app', \Illuminate\Support\Arr::except(get_defined_vars(), ['__data', '__path']))->render(); ?><?php /**PATH /Users/ryan/Documents/Programming/INET2005 PHP/laravel/finalassignment-ryangosborne/SMFeed-w0201814/resources/views/user/show.blade.php ENDPATH**/ ?>