
  Pod::Spec.new do |s|
    s.name = 'CapacitorFileChooser'
    s.version = '0.0.1'
    s.summary = 'File Chooser for Ionic Capacitor (only Android)'
    s.license = 'Affero'
    s.homepage = 'https://github.com/dmartinlozano/capacitor-file-chooser.git'
    s.author = 'dmartinlozano'
    s.source = { :git => 'https://github.com/dmartinlozano/capacitor-file-chooser.git', :tag => s.version.to_s }
    s.source_files = 'ios/Plugin/**/*.{swift,h,m,c,cc,mm,cpp}'
    s.ios.deployment_target  = '11.0'
    s.dependency 'Capacitor'
  end