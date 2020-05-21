package com.castac.beatbox;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.castac.beatbox.databinding.FragmentBeatBoxBinding;
import com.castac.beatbox.databinding.ListItemSoundBinding;

public class BeatBoxFragment extends Fragment {

    public static  BeatBoxFragment newInstance(){
        return new BeatBoxFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentBeatBoxBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_beat_box , container, false);

        binding.recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));

        return binding.getRoot();
    }

    private class SoundHolder extends RecyclerView.ViewHolder {
        private ListItemSoundBinding mBinding;

        public SoundHolder(@NonNull ListItemSoundBinding itemView) {
            super(itemView.getRoot());
            mBinding = itemView;
        }
    }
}
