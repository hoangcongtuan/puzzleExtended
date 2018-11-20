package com.tuanhc.puzzleextended;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import com.tuanhc.puzzleextended.circle.CricleLayout;
import com.xiaopo.flying.puzzle.PuzzleLayout;
import com.xiaopo.flying.puzzle.PuzzlePiece;
import com.xiaopo.flying.puzzle.PuzzleView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getName();
    @BindView(R.id.puzzle_view)
    PuzzleView puzzleView;

    /**
     * variable
     */
    private List<String> bitmapPaint = null;
    private PuzzleLayout puzzleLayout;
    private List<Target> targets = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

//        puzzleLayout = PuzzleUtils.getPuzzleLayout(1, 2, 0);
        puzzleLayout = new CricleLayout(0);
        puzzleView.setPuzzleLayout(puzzleLayout);
        puzzleView.setTouchEnable(true);
        puzzleView.setNeedDrawLine(false);
        puzzleView.setNeedDrawOuterLine(false);
        puzzleView.setLineSize(4);
        puzzleView.setLineColor(Color.BLACK);
        puzzleView.setSelectedLineColor(Color.BLACK);
        puzzleView.setHandleBarColor(Color.BLACK);
        puzzleView.setAnimateDuration(300);
        puzzleView.setOnPieceSelectedListener(new PuzzleView.OnPieceSelectedListener() {
            @Override public void onPieceSelected(PuzzlePiece piece, int position) {
                Snackbar.make(puzzleView, "Piece " + position + " selected", Snackbar.LENGTH_SHORT).show();
            }
        });

        // currently the SlantPuzzleLayout do not support padding
        puzzleView.setPiecePadding(10);
        puzzleView.post(new Runnable() {
            @Override public void run() {
                loadPhoto();
            }
        });

        Log.d(TAG, "onCreate: End Create");


    }

    private void loadPhoto() {
        loadPhotoFromRes();
    }

    private void loadPhotoFromRes() {
        final List<Bitmap> pieces = new ArrayList<>();

        final int[] resIds = new int[] {
                R.drawable.demo1, R.drawable.demo2, R.drawable.demo3, R.drawable.demo4, R.drawable.demo5,
                R.drawable.demo6, R.drawable.demo7, R.drawable.demo8, R.drawable.demo9,
        };

        final int count =
                resIds.length > puzzleLayout.getAreaCount() ? puzzleLayout.getAreaCount() : resIds.length;

        for (int i = 0; i < count; i++) {
            final Target target = new Target() {
                @Override public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                    pieces.add(bitmap);
                    if (pieces.size() == count) {
                        if (resIds.length < puzzleLayout.getAreaCount()) {
                            for (int i = 0; i < puzzleLayout.getAreaCount(); i++) {
                                puzzleView.addPiece(pieces.get(i % count));
                            }
                        } else {
                            puzzleView.addPieces(pieces);
                        }
                    }
                    targets.remove(this);
                }

                @Override public void onBitmapFailed(Drawable errorDrawable) {

                }

                @Override public void onPrepareLoad(Drawable placeHolderDrawable) {

                }
            };

            Picasso.with(this).load(resIds[i]).config(Bitmap.Config.RGB_565).into(target);
            targets.add(target);
        }
    }

}
