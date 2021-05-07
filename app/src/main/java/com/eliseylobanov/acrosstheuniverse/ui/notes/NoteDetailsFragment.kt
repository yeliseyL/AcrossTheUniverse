package com.eliseylobanov.acrosstheuniverse.ui.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.eliseylobanov.acrosstheuniverse.R
import com.eliseylobanov.acrosstheuniverse.databinding.FragmentNoteDetailsBinding

class NoteDetailsFragment : Fragment(R.layout.fragment_note_details) {

    lateinit var viewModel: NoteDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentNoteDetailsBinding.inflate(inflater)
        binding.lifecycleOwner = this

        val note = NoteDetailsFragmentArgs.fromBundle(requireArguments()).note
        val activity = requireNotNull(this.activity)
        viewModel = ViewModelProvider(this, NoteDetailsViewModel.Factory(activity.application, note))
            .get(NoteDetailsViewModel::class.java)
        binding.note = viewModel.note

        binding.noteFab.setOnClickListener {
            viewModel.titleText = binding.titleEditText.text.toString()
            viewModel.noteText = binding.noteEditText.text.toString()
            viewModel.createNote()
            view?.findNavController()?.navigateUp()
        }

        return binding.root
    }

}