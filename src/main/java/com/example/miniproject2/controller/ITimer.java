package com.example.miniproject2.controller;

/**
 * The {@code ITimer} interface defines the methods required for a timer component
 * within the Sudoku application. Implementing classes should provide the functionality
 * to manage the timer's lifecycle and display.
 */
public interface ITimer {

    /**
     * Starts the timer. The timer begins counting time.
     */
    void startTimer();

    /**
     * Stops the timer. The timer halts its counting until restarted.
     */
    void stopTimer();

    /**
     * Resets the timer to its initial state. The elapsed time is cleared.
     */
    void resetTimer();

    /**
     * Updates the display label of the timer to show the current elapsed time.
     */
    void updateLabel();
}
