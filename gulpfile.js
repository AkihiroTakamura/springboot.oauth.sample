var gulp = require('gulp');
var gulpUtil = require('gulp-util');
var sass = require('gulp-sass');
var autoprefixer = require('gulp-autoprefixer');
var uglify = require('gulp-uglify');
var plumber = require('gulp-plumber');
var browserify = require('browserify');
var source = require('vinyl-source-stream');
var buffer = require('vinyl-buffer');
var sourcemaps = require('gulp-sourcemaps');
var concat = require('gulp-concat');
var git = require('gulp-git');
var reactify = require('reactify');
var browserifyCss = require('browserify-css');

gulp.task("sass", function() {
  gulp.src("./frontend/sass/**/*scss")
    .pipe(plumber())
    .pipe(concat('style.scss'))
    .pipe(sass())
    .pipe(autoprefixer())
    .pipe(gulp.dest("./src/main/resources/public/css"))
});

gulp.task('js', function() {
  browserify({
    entries: ["./frontend/js/main.js"], // ビルド対象のファイル
    debug: true // sourcemapを出力、chromeでのdebug可能にする
  })
  .transform(reactify)
  .transform(browserifyCss)
  .bundle()
  .on('error', console.error.bind(console)) // js compileエラーでもwatchを止めない
  .pipe(source("app.js")) // ビルド後のファイル名
  .pipe(buffer())
  .pipe(sourcemaps.init({loadMaps: true}))
  .pipe(sourcemaps.write("./"))
  .pipe(gulp.dest("./src/main/resources/public/js/"));
});

gulp.task('js-release', function() {
  browserify({
    entries: ["./frontend/js/main.js"],
    debug: true
  })
  .transform(reactify)
  .transform(browserifyCss)
  .bundle()
  .on('error', console.error.bind(console))
  .pipe(source("app.js"))
  .pipe(buffer())
  .pipe(sourcemaps.init({loadMaps: true}))
  .pipe(uglify().on('error', gulpUtil.log)) // release時uglifyを実施
  .pipe(sourcemaps.write("./"))
  .pipe(gulp.dest("./src/main/resources/public/js/"));
});

gulp.task('clonePlugin', function() {
  git.clone('https://github.com/RiptideElements/pdfjs-build.git', {cwd: "./src/main/resources/public/plugin"});

});

gulp.task('watch', function() {
  gulp.watch(["./frontend/js/**/*.js"], ["js"]);
  gulp.watch(["./frontend/sass/**/*.scss"], ["sass", "js"]); // jsでcssをrequireしているのでjsも実行する
});

gulp.task("init", [
  'clonePlugin'
]);

gulp.task("default", [
  'sass',
  'js',
  'watch'
]);

gulp.task("build", [
  'sass',
  'js-release',
  'clonePlugin'
]);
