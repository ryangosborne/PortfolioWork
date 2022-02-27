<?php $__env->startSection('content'); ?>
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card">

                    <form action="/admin/themes" method="POST" class="pb-3">
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
                            <h4 class="pt-2">Create New Theme</h4>
                        </div>

                        <div class="form-group col-md mt-3">
                            <label for="name">Theme Name</label>
                            <input name="name" type="text" value="<?php echo e(old('name')); ?>" class="form-control" id="name" aria-describedby="nameHelp">
                            <small id="nameHelp" class="form-text text-muted">Enter the name for your theme</small>
                            <?php $__errorArgs = ['name'];
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
                            <label for="themeLink">Theme URL</label>
                            <input type="url" name="themeLink" class="form-control" id="themeLink" value="<?php echo e(old('themeLink')); ?>">
                            <small id="themeLinkHelp" class="form-text text-muted">Enter the link for your theme</small>
                            <?php $__errorArgs = ['themeLink'];
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
                            <button type="submit" class="btn btn-success mr-2" style="float:left">Create</button>
                            <a href="/admin/themes" class="btn btn-primary">Back to Themes</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

<?php $__env->stopSection(); ?>

<?php echo $__env->make('layouts.app', \Illuminate\Support\Arr::except(get_defined_vars(), ['__data', '__path']))->render(); ?><?php /**PATH /Users/ryan/Documents/Programming/INET2005 PHP/laravel/finalassignment-ryangosborne/SMFeed-w0201814/resources/views/theme/create.blade.php ENDPATH**/ ?>