<?php $__env->startSection('content'); ?>

    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card">

                    <form action="/post/<?php echo e($post->id); ?>" method="POST" class="pb-3">
                        <?php echo method_field('PUT'); ?>
                        <?php echo csrf_field(); ?>

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

                        <div class="card-header col-md">
                            <h4 class="pt-2">Edit Post</h4>
                        </div>

                        <div class="form-group col-md mt-3">
                            <label for="title">Post Title</label>
                            <input name="title" type="text" value="<?php echo e(old('title', $post->title)); ?>" class="form-control" id="title" aria-describedby="titleHelp">
                            <small id="titleHelp" class="form-text text-muted">Edit the title for your post, or don't</small>
                            <?php $__errorArgs = ['title'];
$__bag = $errors->getBag($__errorArgs[1] ?? 'default');
if ($__bag->has($__errorArgs[0])) :
if (isset($message)) { $__messageOriginal = $message; }
$message = $__bag->first($__errorArgs[0]); ?>
                                <div class="alert alert-danger"> <?php echo e($message); ?></div>
                            <?php unset($message);
if (isset($__messageOriginal)) { $message = $__messageOriginal; }
endif;
unset($__errorArgs, $__bag); ?>
                        </div>

                        <div class="form-group col-md">
                            <label for="quote">Quote</label>
                            <input type="text" name="quote" class="form-control" id="quote" value="<?php echo e(old('quote', $post->quote)); ?>">
                            <small id="quoteHelp" class="form-text text-muted">Edit the quote you want to share, or don't</small>
                            <?php $__errorArgs = ['quote'];
$__bag = $errors->getBag($__errorArgs[1] ?? 'default');
if ($__bag->has($__errorArgs[0])) :
if (isset($message)) { $__messageOriginal = $message; }
$message = $__bag->first($__errorArgs[0]); ?>
                                <div class="alert alert-danger"> <?php echo e($message); ?></div>
                            <?php unset($message);
if (isset($__messageOriginal)) { $message = $__messageOriginal; }
endif;
unset($__errorArgs, $__bag); ?>
                        </div>

                        <div class="col-md mt-3">
                            <button type="submit" class="btn btn-success mr-2" style="float:left">Submit</button>
                            <a href="/home" class="btn btn-primary">Back to Feed</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
<?php $__env->stopSection(); ?>

<?php echo $__env->make('layouts.app', \Illuminate\Support\Arr::except(get_defined_vars(), ['__data', '__path']))->render(); ?><?php /**PATH /Users/ryan/Documents/Programming/INET2005 PHP/laravel/finalassignment-ryangosborne/SMFeed-w0201814/resources/views/post/edit.blade.php ENDPATH**/ ?>