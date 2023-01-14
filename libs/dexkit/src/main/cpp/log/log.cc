#include <iostream>
#include <android/log.h>

int __android_log_print(int prio, const char *tag,  const char *fmt, ...) {
    va_list varg1;
    va_list varg2;
    if (fmt == nullptr) {
        return -1;
    }
    va_start(varg1, fmt);
    va_copy(varg2, varg1);
    int size = vsnprintf(nullptr, 0, fmt, varg1) + 4;
    va_end(varg1);
    if (size <= 0) {
        return -1;
    }
    void *buffer = malloc(size);
    if (buffer == nullptr) {
        return -1;
    }
    va_start(varg2, fmt);
    vsnprintf((char *) buffer, size, fmt, varg2);
    va_end(varg2);
    char level;
    switch (prio) {
        case ANDROID_LOG_VERBOSE:
            level = 'V';
            break;
        case ANDROID_LOG_DEBUG:
            level = 'D';
            break;
        case ANDROID_LOG_INFO:
            level = 'I';
            break;
        case ANDROID_LOG_WARN:
            level = 'W';
            break;
        case ANDROID_LOG_ERROR:
            level = 'E';
            break;
        case ANDROID_LOG_FATAL:
            level = 'F';
            break;
        default:
            level = 'D';
            break;
    }
    std::cout << level <<tag << " ==> " << static_cast<const char *>(buffer) << std::endl;
    return 0;
}