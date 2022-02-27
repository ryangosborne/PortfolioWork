<?php $__env->startSection('content'); ?>
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="card pb-lg-2">
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
                        <?php if(Session::has('messageDanger')): ?>
                            <div class="alert-danger" style="padding: 20px; font-size: medium">
                                <?php echo e(Session::get('messageDanger')); ?>

                            </div>
                        <?php endif; ?>
                        <?php if(Session::has('messageSuccess')): ?>
                            <div class="alert-success" style="padding: 20px; font-size: medium">
                                <?php echo e(Session::get('messageSuccess')); ?>

                            </div>
                        <?php endif; ?>
                        <?php $__currentLoopData = $posts; $__env->addLoop($__currentLoopData); foreach($__currentLoopData as $post): $__env->incrementLoopIndices(); $loop = $__env->getLastLoop(); ?>
                            <table class="table-light" style="border:4px double dodgerblue; margin: 8px 16px 8px 16px">
                                <tr>
                                    <th style="height:80px">
                                        <h5 class="pl-lg-3"><?php echo e($post->title); ?></h5>
                                    </th>
                                </tr>
                                <tr>
                                    <td class="pl-lg-3 pr-lg-2 pb-3" style="font-size: medium">
                                        <?php echo e($post->quote); ?>

                                    </td>
                                </tr>
                                <tr>
                                    <td class="pl-lg-3" style="height:50px; font-size: medium; font-style: italic">
                                        Posted by <?php echo e($users[$post->created_by-1]->name); ?>

                                        on <?php echo e($post->created_at->format("D M. d, Y H:i")); ?>

                                    </td>
                                </tr>
                                <!-- checking if a user is logged in -->
                                <?php if(auth()->guard()->check()): ?>
                                    <tr>
                                        <!-- checking if user created this post -->
                                        <?php if($users[$post->created_by-1]->id == Auth::user()->id): ?>
                                            <td class="pl-lg-3 pb-3">
                                                <a class="btn btn-warning" style="width: 100px" href="/post/<?php echo e($post->id); ?>/edit" role="button">Edit</a>
                                            </td>
                                        <?php endif; ?>
                                        <!-- checking if user created this post OR user is Moderator -->
                                        <?php if(($users[$post->created_by-1]->id == Auth::user()->id) || ($users->find(Auth::user()->id)->roles()->find('2'))): ?>
                                            <td class="pb-3 pr-3" style="float: right">
                                                <form action="/post/<?php echo e($post->id); ?>" method="POST">
                                                    <?php echo method_field('DELETE'); ?>
                                                    <?php echo csrf_field(); ?>
                                                    <button class="btn btn-danger" style="width: 100px" type="submit">Delete</button>
                                                </form>
                                            </td>
                                        <?php endif; ?>
                                    </tr>
                                <?php endif; ?>
                            </table>
                        <?php endforeach; $__env->popLoop(); $loop = $__env->getLastLoop(); ?>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<?php $__env->stopSection(); ?>

<?php echo $__env->make('layouts.app', \Illuminate\Support\Arr::except(get_defined_vars(), ['__data', '__path']))->render(); ?><?php /**PATH /Users/ryan/Documents/Programming/INET2005 PHP/laravel/finalassignment-ryangosborne/SMFeed-w0201814/resources/views//home.blade.php ENDPATH**/ ?>