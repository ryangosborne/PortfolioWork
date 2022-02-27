<?php $__env->startSection('content'); ?>
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card">
                    <table class="table table-striped">
                        <tr>
                            <th>
                                <h4 class="pt-2">Theme Details for <?php echo e($theme->name); ?></h4>
                            </th>
                            <th>
                                <a href="/admin/themes" class="btn btn-primary" style="float:right">Back to List</a>
                            </th>
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
                            <tr class="table-light">
                                <td colspan="8">Name: <?php echo e($theme->name); ?><br>
                                    CDN URL: <?php echo e($theme->cdn_url); ?><br>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
<?php $__env->stopSection(); ?>

<?php echo $__env->make('layouts.app', \Illuminate\Support\Arr::except(get_defined_vars(), ['__data', '__path']))->render(); ?><?php /**PATH /Users/ryan/Documents/Programming/INET2005 PHP/laravel/finalassignment-ryangosborne/SMFeed-w0201814/resources/views/theme/show.blade.php ENDPATH**/ ?>