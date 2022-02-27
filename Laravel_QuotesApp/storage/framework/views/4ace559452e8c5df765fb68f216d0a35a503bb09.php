<?php $__env->startSection('content'); ?>
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card">
                    <table class="table table-striped">
                        <tr>
                            <th>
                                <h4 class="pt-2">Theme Administration</h4>
                            </th>
                            <th>
                                <a class="btn btn-primary" href="/admin/themes/create" style="float:right">Create New Theme</a>
                            </th>
                        </tr>
                        <tr>
                            <th>Name</th>
                            <th>Actions</th>
                        </tr>
                        <tbody>
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
                        <?php $__currentLoopData = $themes; $__env->addLoop($__currentLoopData); foreach($__currentLoopData as $theme): $__env->incrementLoopIndices(); $loop = $__env->getLastLoop(); ?>
                            <tr>
                                <td><?php echo e($theme->name); ?></td>
                                <td colspan="1" class="col-md-6">
                                    <a class="btn btn-success" href="/admin/themes/<?php echo e($theme->id); ?>" role="button">Info</a>
                                    <a class="btn btn-warning ml-4" href="/admin/themes/<?php echo e($theme->id); ?>/edit" style="float: inside" role="button">Edit</a>
                                    <form action="/admin/themes/<?php echo e($theme->id); ?>" method="POST" style="float:right" class="col-md-6 mr-2">
                                        <?php echo method_field('DELETE'); ?>
                                        <?php echo csrf_field(); ?>
                                        <button class="btn btn-danger" type="submit">Delete</button>
                                    </form>
                                </td>
                            </tr>
                        <?php endforeach; $__env->popLoop(); $loop = $__env->getLastLoop(); ?>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
<?php $__env->stopSection(); ?>

<?php echo $__env->make('layouts.app', \Illuminate\Support\Arr::except(get_defined_vars(), ['__data', '__path']))->render(); ?><?php /**PATH /Users/ryan/Documents/Programming/INET2005 PHP/laravel/finalassignment-ryangosborne/SMFeed-w0201814/resources/views/theme/index.blade.php ENDPATH**/ ?>