package com.mzu.sudoku;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class SudokuBoardView extends View {
    private static final int BOARD_SIZE = 9;
    private int cellSize;
    private final Paint thinLinePaint = new Paint();
    private final Paint boldLinePaint = new Paint();
    private final Paint cellBackgroundPaint = new Paint();
    private final Paint fixedNumberPaint = new Paint();
    private final Paint userNumberPaint = new Paint();
    private final Paint highlightPaint = new Paint();

    private int selectedRow = -1;
    private int selectedCol = -1;
    private int[][] puzzle = new int[BOARD_SIZE][BOARD_SIZE];
    private boolean[][] fixedCells = new boolean[BOARD_SIZE][BOARD_SIZE];

    public SudokuBoardView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        thinLinePaint.setColor(getResources().getColor(R.color.thin_line));
        thinLinePaint.setStrokeWidth(2);

        boldLinePaint.setColor(getResources().getColor(R.color.bold_line));
        boldLinePaint.setStrokeWidth(4);

        cellBackgroundPaint.setColor(getResources().getColor(R.color.cell_background));
        fixedNumberPaint.setColor(getResources().getColor(R.color.fixed_number));
        fixedNumberPaint.setTextAlign(Paint.Align.CENTER);
        fixedNumberPaint.setFakeBoldText(true);

        userNumberPaint.setColor(getResources().getColor(R.color.user_number));
        userNumberPaint.setTextAlign(Paint.Align.CENTER);

        highlightPaint.setColor(getResources().getColor(R.color.highlight_cell));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int size = Math.min(getMeasuredWidth(), getMeasuredHeight());
        cellSize = size / BOARD_SIZE;
        setMeasuredDimension(size, size);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawBackground(canvas);
        drawNumbers(canvas);
        drawLines(canvas);
        highlightSelectedCell(canvas);
    }

    private void drawBackground(Canvas canvas) {
        canvas.drawRect(0, 0, getWidth(), getHeight(), cellBackgroundPaint);
    }

    private void drawLines(Canvas canvas) {
        // Draw thin lines
        for (int i = 1; i < BOARD_SIZE; i++) {
            float pos = i * cellSize;
            // Vertical
            canvas.drawLine(pos, 0, pos, getHeight(),
                    (i % 3 == 0) ? boldLinePaint : thinLinePaint);
            // Horizontal
            canvas.drawLine(0, pos, getWidth(), pos,
                    (i % 3 == 0) ? boldLinePaint : thinLinePaint);
        }
    }

    private void drawNumbers(Canvas canvas) {
        fixedNumberPaint.setTextSize(cellSize * 0.6f);
        userNumberPaint.setTextSize(cellSize * 0.6f);

        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                if (puzzle[row][col] != 0) {
                    String text = String.valueOf(puzzle[row][col]);
                    float x = col * cellSize + cellSize / 2f;
                    float y = row * cellSize + cellSize / 2f -
                            ((fixedNumberPaint.descent() + fixedNumberPaint.ascent()) / 2);

                    Paint paint = fixedCells[row][col] ? fixedNumberPaint : userNumberPaint;
                    canvas.drawText(text, x, y, paint);
                }
            }
        }
    }

    private void highlightSelectedCell(Canvas canvas) {
        if (selectedRow != -1 && selectedCol != -1) {
            canvas.drawRect(
                    selectedCol * cellSize,
                    selectedRow * cellSize,
                    (selectedCol + 1) * cellSize,
                    (selectedRow + 1) * cellSize,
                    highlightPaint
            );
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            selectedCol = (int) (event.getX() / cellSize);
            selectedRow = (int) (event.getY() / cellSize);
            invalidate();
            return true;
        }
        return super.onTouchEvent(event);
    }

    public void setNumber(int number) {
        if (selectedRow != -1 && selectedCol != -1 && !fixedCells[selectedRow][selectedCol]) {
            puzzle[selectedRow][selectedCol] = number;
            invalidate();
        }
    }

    public void setPuzzle(int[][] newPuzzle, boolean[][] fixed) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            System.arraycopy(newPuzzle[i], 0, puzzle[i], 0, BOARD_SIZE);
            System.arraycopy(fixed[i], 0, fixedCells[i], 0, BOARD_SIZE);
        }
        invalidate();
    }
}
