<?php $__env->startSection('content'); ?>
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="card">
                <table class="table table-striped">
                    <tr>
                        <th colspan="10">
                            <h4 class="pt-2">The Feed</h4>
                        </th>
                        <?php if(Auth::check()): ?>
                            <th colspan="1">
                                <a href="/post/create" class="btn btn-primary" style="float:right">Create New Post</a>
                            </th>
                        <?php endif; ?>
                    </tr>
                    <tbody>
                        <?php $__currentLoopData = $posts; $__env->addLoop($__currentLoopData); foreach($__currentLoopData as $post): $__env->incrementLoopIndices(); $loop = $__env->getLastLoop(); ?>
                            <table class="table-light" style="border:4px double dodgerblue; margin: 8px 16px 8px 16px">
                                <tr>
                                    <th style="height:80px">
                                        <h5 class="pl-lg-3"><?php echo e($post->title); ?></h5>
                                    </th>
                                </tr>
                                <tr>
                                    <td class="pl-lg-3" style="font-size: medium">
                                        <?php echo e($post->quote); ?>

                                    </td>
                                </tr>
                                <tr>
                                    <td class="pl-lg-3" style="height:50px; font-size: medium">
                                        Posted by <?php echo e($post->created_by); ?>

                                    </td>
                                </tr>
                            </table>
                        <?php endforeach; $__env->popLoop(); $loop = $__env->getLastLoop(); ?>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<?php $__env->stopSection(); ?>

<?php echo $__env->make('layouts.app', \Illuminate\Support\Arr::except(get_defined_vars(), ['__data', '__path']))->render(); ?><?php /**PATH /Users/ryan/Documents/Programming/INET2005 PHP/laravel/finalassignment-ryangosborne/SMFeed-w0201814/resources/views/home.blade.php ENDPATH**/ ?>