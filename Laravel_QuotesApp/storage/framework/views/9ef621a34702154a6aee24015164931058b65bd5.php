<?php $__env->startSection('content'); ?>
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card">

                    <form action="/admin/users/<?php echo e($user->id); ?>" method="POST" class="pb-3">

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
                            <h4 class="pt-2">Edit User</h4>
                        </div>

                        <div class="form-group col-md mt-3">
                            <label for="name">Name</label>
                            <input name="name" type="text" value="<?php echo e(old('name', $user->name)); ?>" class="form-control" id="name" aria-describedby="nameHelp">
                            <small id="nameHelp" class="form-text text-muted">Enter your name, or update it</small>
                            <?php $__errorArgs = ['name'];
$__bag = $errors->getBag($__errorArgs[1] ?? 'default');
if ($__bag->has($__errorArgs[0])) :
if (isset($message)) { $__messageOriginal = $message; }
$message = $__bag->first($__errorArgs[0]); ?>
                                <div class="alert alert-danger"><?php echo e($message); ?></div>
                            <?php unset($message);
if (isset($__messageOriginal)) { $message = $__messageOriginal; }
endif;
unset($__errorArgs, $__bag); ?>
                        </div>

                        <div class="form-group col-md">
                            <label for="email">Email</label>
                            <input type="email" name="email" class="form-control" id="email" value="<?php echo e(old('email', $user->email)); ?>">
                            <small id="emailHelp" class="form-text text-muted">Enter your email, or update it</small>
                            <?php $__errorArgs = ['email'];
$__bag = $errors->getBag($__errorArgs[1] ?? 'default');
if ($__bag->has($__errorArgs[0])) :
if (isset($message)) { $__messageOriginal = $message; }
$message = $__bag->first($__errorArgs[0]); ?>
                                <div class="alert alert-danger"><?php echo e($message); ?></div>
                            <?php unset($message);
if (isset($__messageOriginal)) { $message = $__messageOriginal; }
endif;
unset($__errorArgs, $__bag); ?>
                        </div>

                        <div class="form-group col-md">
                            <label for="roles">Roles</label>
                            <small id="rolesHelp" class="form-text text-muted">Change the user's roles</small>
                        </div>

                        <!-- looping through roles, checking if user has role, if yes, check box -->
                        <div class="form-group col-md">
                            <?php $__currentLoopData = $roles; $__env->addLoop($__currentLoopData); foreach($__currentLoopData as $role): $__env->incrementLoopIndices(); $loop = $__env->getLastLoop(); ?>

                                <div>
                                    <input type="checkbox" id="<?php echo e($role->name); ?>" name="roles[]" value="<?php echo e(( $loop->index) + 1); ?>"
                                    <?php echo e($user->roles()->find($role)==true ? 'checked' : ''); ?>>

                                    <label for="<?php echo e($role->name); ?>"><?php echo e($role->name); ?></label>
                                </div>

                            <?php endforeach; $__env->popLoop(); $loop = $__env->getLastLoop(); ?>
                            <?php $__errorArgs = ['roles'];
$__bag = $errors->getBag($__errorArgs[1] ?? 'default');
if ($__bag->has($__errorArgs[0])) :
if (isset($message)) { $__messageOriginal = $message; }
$message = $__bag->first($__errorArgs[0]); ?>
                                <div class="alert alert-danger"><?php echo e($message); ?></div>
                            <?php unset($message);
if (isset($__messageOriginal)) { $message = $__messageOriginal; }
endif;
unset($__errorArgs, $__bag); ?>
                        </div>


                        <div class="col-md mt-3">
                            <button type="submit" class="btn btn-success mr-2" style="float:left">Submit</button>
                            <a href="/admin/users" class="btn btn-primary">Back to List</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
<?php $__env->stopSection(); ?>

<?php echo $__env->make('layouts.app', \Illuminate\Support\Arr::except(get_defined_vars(), ['__data', '__path']))->render(); ?><?php /**PATH /Users/ryan/Documents/Programming/INET2005 PHP/laravel/finalassignment-ryangosborne/SMFeed-w0201814/resources/views/user/edit.blade.php ENDPATH**/ ?>