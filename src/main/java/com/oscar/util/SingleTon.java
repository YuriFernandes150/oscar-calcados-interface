package com.oscar.util;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

public class SingleTon {

    private static File f;
    private static FileChannel channel;
    private static FileLock lock;

    public static void lock() {

        try {
            f = new File("locker.lock");

            if (f.exists()) {
                f.delete();
            }
            channel = new RandomAccessFile(f, "rw").getChannel();
            lock = channel.tryLock();
            if (lock == null) {

                channel.close();
                JOptionPane.showMessageDialog(null, "O app já está rodando, feche a instância atual antes de iniciar outra.", "AVISO", 2);
                System.exit(0);
            }

            Thread shutdown = new Thread(() -> {
                unlock();
            });
            Runtime.getRuntime().addShutdownHook(shutdown);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void unlock() {

        try {

            if (lock != null) {

                lock.release();
                channel.close();
                f.delete();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
